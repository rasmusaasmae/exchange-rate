"use client";

import { convert } from "@/api/convert";
import { Currency } from "@/api/schemas";
import { Input } from "@/components/ui/input";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { useQuery } from "@tanstack/react-query";
import { XIcon } from "lucide-react";
import { useState } from "react";
import { Label } from "./ui/label";

type ConverterProps = {
  currencies: Currency[];
};

const Converter = (props: ConverterProps) => {
  const { currencies } = props;
  const [from, setFrom] = useState<Currency>("EUR");
  const [to, setTo] = useState<Currency>("USD");
  const [amountStr, setAmountStr] = useState<string>("");
  const amount = isNaN(parseFloat(amountStr)) ? 0 : parseFloat(amountStr);

  const { data: converted } = useQuery({
    queryKey: ["convert", from, to, amount],
    queryFn: () => convert(from, to, amount),
  });

  return (
    <div className="flex w-full max-w-xl flex-col items-center space-y-4 rounded-md border border-emerald-500 p-10 shadow-xl shadow-emerald-500">
      <h1 className="text-2xl font-bold">Currency converter</h1>
      <div className="flex flex-col space-y-2">
        <Label htmlFor="amount">Convert</Label>
        <div className="flex w-full items-center space-x-2">
          <Input
            id="amount"
            inputMode="decimal"
            value={amountStr}
            onChange={(e) =>
              setAmountStr(e.target.value.replace(/[^0-9.]/g, ""))
            }
            placeholder="0"
          />
          <Select value={from} onValueChange={setFrom}>
            <SelectTrigger className="w-24 cursor-pointer">
              <SelectValue />
            </SelectTrigger>
            <SelectContent>
              {currencies?.map((currency) => (
                <SelectItem key={`from-${currency}`} value={currency}>
                  {currency}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>
      </div>
      <div className="flex w-full max-w-sm items-center justify-between space-x-2 text-neutral-300">
        <span className="flex flex-row items-center space-x-1 text-emerald-500">
          <XIcon className="h-4 w-4" />
          <span>{converted?.rate}</span>
        </span>
        <span>Conversion rate</span>
      </div>
      <div className="flex flex-col space-y-2">
        <Label htmlFor="converted">Adding to</Label>
        <div className="flex w-full items-center space-x-2">
          <Input
            id="converted"
            value={converted?.amount.toFixed(2) ?? ""}
            disabled
            className="disabled:opacity-100"
          />
          <Select value={to} onValueChange={setTo}>
            <SelectTrigger className="w-24 cursor-pointer">
              <SelectValue />
            </SelectTrigger>
            <SelectContent>
              {currencies?.map((currency) => (
                <SelectItem key={`to-${currency}`} value={currency}>
                  {currency}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>
      </div>
    </div>
  );
};

export default Converter;
