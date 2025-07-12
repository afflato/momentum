import { Fragment } from "react"; 
import StockListing from "./StockListing";
import StockAnalysis from "./StockAnalysis";

const StockListings = ({ data, strategy }) => {

  const isCommonOnly = data && data.common?.length > 0 && data.incoming?.length === 0 && data.outgoing?.length === 0;

  let content = null;

  if (!data) {
    content = null;
  } else if (isCommonOnly) {
    content = (
      <StockListing
        items={data.common}
        heading={strategy}
      />
    );
  } else {
    content = (
      <Fragment>
          <StockListing items={data.incoming} heading="ADDED" />
          <StockListing items={data.outgoing} heading="REMOVED" />
          <StockListing items={data.common} heading="COMMON" />
      </Fragment>
    );
  }

  return (
    data && (
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 ml-4">
        {content}

        {isCommonOnly && (
          <StockAnalysis />
        )}
      </div>
    )
  );
};

export default StockListings;