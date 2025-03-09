import { z } from "zod";
import { CurrencyGrowth, currencyGrowthSchema } from "./schemas";

export const getCurrencyGrowths = async (): Promise<CurrencyGrowth[]> => {
  const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/growth`, {
    headers: {
      Accept: "application/json",
    },
  });
  const data = await response.json();
  return z.array(currencyGrowthSchema).parse(data);
};
