import { z } from "zod";

export const currencySchema = z.string().length(3);
export type Currency = z.infer<typeof currencySchema>;

export const conversionResultSchema = z.object({
  amount: z.number(),
  rate: z.number(),
});
export type ConversionResult = z.infer<typeof conversionResultSchema>;
