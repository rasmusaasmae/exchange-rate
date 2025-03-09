import { CurrencyGrowth } from "@/api/schemas";
import { cn } from "@/lib/utils";

type GrowthProps = {
  currencyGrowths: CurrencyGrowth[];
  title: string;
  description: string;
} & React.HTMLAttributes<HTMLDivElement>;

const Growth = (props: GrowthProps) => {
  const { currencyGrowths, title, description, className, ...rest } = props;

  return (
    <div
      className={cn(
        "flex w-full max-w-xl flex-col items-center rounded-md border border-emerald-500 p-4",
        className,
      )}
      {...rest}
    >
      <h1 className="mb-4 text-2xl font-bold">{title}</h1>
      <p className="mb-4 text-sm text-neutral-500">{description}</p>
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
