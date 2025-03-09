import { CurrencyGrowth } from "@/api/schemas";
import { cn } from "@/lib/utils";

type GrowthProps = {
  currencyGrowths: CurrencyGrowth[];
};

const Growth = (props: GrowthProps) => {
  const { currencyGrowths } = props;

  return (
    <div className="flex w-full max-w-xl flex-col items-center rounded-md border border-emerald-500 p-4">
      <h1 className="mb-4 text-2xl font-bold">Top 5 currencies</h1>
      <p className="mb-4 text-sm text-neutral-500">
        Currencies with the highest growth in the last 10 days
      </p>
      <ul className="flex w-fit flex-col space-y-2">
        {currencyGrowths.map((growth) => (
          <li
            key={growth.currency}
            className="flex w-full items-center justify-between space-x-2"
          >
            <span>{growth.currency}</span>
            <span
              className={cn(
                "text-sm",
                growth.growthRate > 0 ? "text-green-500" : "text-red-500",
              )}
            >
              {growth.growthRate > 0 && "+"}
              {(100 * growth.growthRate).toFixed(2)}%
            </span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Growth;
