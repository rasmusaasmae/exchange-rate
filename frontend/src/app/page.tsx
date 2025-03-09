import { getCurrencies } from "@/api/get-currencies";
import { getCurrencyGrowths } from "@/api/get-currency-growth";
import Converter from "@/components/converter";
import Growth from "@/components/growth";

export default async function Home() {
  const currencies = await getCurrencies();
  const currencyGrowths = await getCurrencyGrowths();
  return (
    <main className="min-h-screen w-full grid place-items-center p-4 xl:p-20">
      <div className="grid xl:grid-cols-3 grid-cols-1 gap-16">
        <div className="hidden sm:block" />
        <Converter currencies={currencies} />
        <Growth currencyGrowths={currencyGrowths} />
      </div>
    </main>
  );
}
