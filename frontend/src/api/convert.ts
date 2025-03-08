import { ConversionResult, conversionResultSchema, Currency } from "./schemas";

export const convert = async (
  from: Currency,
  to: Currency,
  amount: number
): Promise<ConversionResult> => {
  const response = await fetch(
    `${process.env.NEXT_PUBLIC_API_URL}/convert/${from}/${to}?amount=${amount}`,
    {
      headers: {
        Accept: "application/json",
      },
    }
  );
  const data = await response.json();
  return conversionResultSchema.parse(data);
};
