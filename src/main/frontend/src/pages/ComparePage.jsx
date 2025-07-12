import { useEffect } from "react";
import CompareCard from "../components/CompareCard";
import StockListings from "../components/StockListings";
import Spinner from "../components/Spinner";
import { useDispatch, useSelector } from "react-redux";
import { clearCompareData } from "../features/compareSlice";

const ComparePage = () => {
  const dispatch = useDispatch();
  
  const { compare, status, error } = useSelector((state) => state.compare);
   useEffect(() => {
    return () => {
      dispatch(clearCompareData());
    };
  }, [dispatch]);
  return (
    <div>
      <CompareCard />
      {status == "loading" ? (
        <Spinner loading={true} />
      ) : (
        <StockListings data={compare} />
      )}
    </div>
  );
};

export default ComparePage;
