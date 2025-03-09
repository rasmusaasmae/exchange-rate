import { z } from "zod";
import { Currency, currencySchema } from "./schemas";

export const getCurrencies = async (): Promise<Currency[]> => {
  const response = await fetch(
    `${process.env.NEXT_PUBLIC_API_URL}/currencies`,
    {
      headers: {
        Accept: "application/json",
      },
    },
  );
  const data = await response.json();
  const currencies = z.array(currencySchema).parse(data);
  currencies.sort();
  return currencies;
};
