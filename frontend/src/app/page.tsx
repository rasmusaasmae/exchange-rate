import { getCurrencies } from "@/api/get-currencies";
import { getCurrencyGrowths } from "@/api/get-currency-growth";
import Converter from "@/components/converter";
import Growth from "@/components/growth";

export default async function Home() {
  const currencies = await getCurrencies();
  const currencyGrowths = await getCurrencyGrowths();
  return (
    <main className="grid min-h-screen w-full place-items-center p-4 xl:p-20">
      <div className="grid grid-cols-1 gap-16 xl:grid-cols-3">
        <Growth
          currencyGrowths={[]}
          title="Changing currencies"
          description="Top 5 currencies with the highest highest growth and decline over a 10-day period in the last 90 days"
          className="order-3 xl:order-1"
        />
        <Converter
          currencies={currencies}
          className="order-1 xl:order-2 xl:scale-110"
        />
        <Growth
          currencyGrowths={currencyGrowths}
          title="Growing currencies"
          description="Top 5 currencies with the highest growth in the last 10 days"
          className="order-2 xl:order-3"
        />
      </div>
    </main>
  );
}
