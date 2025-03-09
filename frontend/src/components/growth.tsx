import { CurrencyGrowth } from "@/api/schemas";
import { cn } from "@/lib/utils";

type GrowthProps = {
  currencyGrowths: CurrencyGrowth[];
};

const Growth = (props: GrowthProps) => {
  const { currencyGrowths } = props;

  return (
    <div className="flex flex-col items-center w-full max-w-xl border-emerald-500 border rounded-md p-4">
      <h1 className="text-2xl font-bold mb-4">Top 5 currencies</h1>
      <p className="text-neutral-500 text-sm mb-4">
        Currencies with the highest growth in the last 10 days
      </p>
      <ul className="flex flex-col space-y-2 w-fit">
        {currencyGrowths.map((growth) => (
          <li
            key={growth.currency}
            className="flex items-center space-x-2 w-full justify-between"
          >
            <span>{growth.currency}</span>
            <span
              className={cn(
                "text-sm",
                growth.growthRate > 0 ? "text-green-500" : "text-red-500"
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
