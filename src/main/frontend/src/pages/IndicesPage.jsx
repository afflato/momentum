import { useEffect } from "react";
import IndexDropDown from "../components/IndexDropDown";
import StockListings from "../components/StockListings";
import Spinner from "../components/Spinner";
import { useDispatch, useSelector } from "react-redux";
import { fetchMomentum, clearMomentumData, setStrategyIndex } from "../features/momentumSlice";
import { toast } from "react-toastify";

const IndicesPage = () => {
  const dispatch = useDispatch();
  const { strategyIndex, momentum, status, error } = useSelector((state) => state.momentum);

  const selectedValue = (event) => {
    const selected = event.target.value;
    dispatch(setStrategyIndex(selected));
    
  };

  const fetchData = () => {
    if (strategyIndex !== "") {
      dispatch(fetchMomentum(strategyIndex));
    } else {
      toast.error("Please select a value from the dropdown!");
    }
  };

  const {  } = useSelector((state) => state.momentum);

  useEffect(() => {
    if (strategyIndex && strategyIndex !== "") {
      dispatch(fetchMomentum(strategyIndex));
    } 

    return () => {
      dispatch(clearMomentumData());
    };
  }, [dispatch]);

  if (status === "failed") {
    return <div>Error: {error}</div>;
  }

  return (
    <div>
      <div className="flex items-center space-x-4">
        <div className="ml-4">
          <IndexDropDown handleChange={selectedValue} category={strategyIndex} label="Select" />
        </div>

        <button
          onClick={fetchData}
          className="bg-black text-white font-bold py-2 px-4 rounded"
        >
          Check
        </button>
      </div>
      {status == "loading" ? (
        <Spinner loading={true} />
      ) : (
        <StockListings data={momentum} strategy={strategyIndex} />
      )}
    </div>
  );
};

export default IndicesPage;
