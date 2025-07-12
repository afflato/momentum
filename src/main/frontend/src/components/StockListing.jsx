import screener from "../assets/images/screener.png";
import topStockResearch from "../assets/images/TopStockResearch.ico";
import moneyworks4me from "../assets/images/moneyworks4me.png";
import StockCard from "./StockCard";

const StockListing = ({ items, heading }) => {
  const categoryClasses = {
    NIFTY50: "bg-teal-500",
    NEXT50: "bg-teal-200",
    MIDCAP: "bg-indigo-300",
    SMLCAP: "bg-fuchsia-300",
    MCRCAP: "bg-rose-200",
  };
  const trHdrClass = "text-gray-900 mb-6 mt-4 rounded-lg p-3 bg-gradient-to-r from-purple-400 to-blue-500 text-white shadow-lg";
  const applyCatClass = (subCategory) => {
    const catClass = categoryClasses[subCategory] || "bg-teal-50";
    return catClass;
  };

  const screenerUrl = (symbol) => {
    return `https://www.screener.in/company/${symbol}/consolidated/`;
  };

  const topStockResearchUrl = (symbol) => {
    return `https://www.topstockresearch.com/rt/Stock/${symbol}/BirdsEyeView`;
  };

  const moneyworks4meUrl = (symbol) => {
    return `https://www.moneyworks4me.com/indianstocks/large-cap/it-ites/it-software/${symbol}/company-info?from=headersearch#tenyr-xray-section`;
  };

  return (
    <div className="border border-gray-200 rounded p-4">
      {items && <h3 className="text-center mb-1 -mt-4">{heading}</h3>}
      <div className="table w-full">
        <div className={`table-row ${trHdrClass}`}>
          <div className="table-cell w-3/10 p-0 border">Symbol</div>
          <div className="table-cell w-1/5 p-0 border">SubCategory</div>
          <div className="table-cell w-1/10 p-0 border">Close</div>
          <div className="table-cell w-1/10 p-0 border">High</div>
          <div className="table-cell w-1/10 p-0 border">Low</div>
        </div>

        {items &&
          items.map((item, index) => (
            <div
              key={index}
              className={`table-row ${applyCatClass(item.subCategory)}`}
            >
              <div className="table-cell w-3/10 p-0 border">
                {item.symbol}

                <StockCard
                  url={screenerUrl(item.symbol)}
                  logo={screener}
                />

                <StockCard
                  url={topStockResearchUrl(item.symbol)}
                  logo={topStockResearch}
                />

                <StockCard
                  url={moneyworks4meUrl(item.symbol)}
                  logo={moneyworks4me}
                />
              </div>
              <div className="table-cell w-1/5 p-0 border">
                {item.subCategory}
              </div>
              <div className="table-cell w-1/10 p-0 border">
                {item.prevClose}
              </div>
              <div className="table-cell w-1/10 p-0 border">
                {item.fiftyTwoWeekHigh}
              </div>
              <div className="table-cell w-1/10 p-0 border">
                {item.fiftyTwoWeekLow}
              </div>
            </div>
          ))}
      </div>
    </div>
  );
};

export default StockListing;