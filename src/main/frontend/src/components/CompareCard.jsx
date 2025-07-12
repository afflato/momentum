import IndexDropDown from "./IndexDropDown";
import {
  fetchCompare,
  setCategory1,
  setCategory2,
} from "../features/compareSlice";
import { useDispatch, useSelector } from "react-redux";
import { toast } from "react-toastify";

const CompareCard = () => {
  const dispatch = useDispatch();

  const { category1, category2 } = useSelector((state) => state.compare);

  const setFromIndex = (event) => {
    const selected = event.target.value;
    dispatch(setCategory1(selected));
  };

  const setToIndex = (event) => {
    const selected = event.target.value;
    dispatch(setCategory2(selected));
  };

  const performCompare = () => {
    if (category1 === "") {
      toast.error("Please select a value from the dropdown labeled- From!");
    } else if (category2 === "") {
      toast.error("Please select a value from the dropdown labeled- To!");
    } else {
      dispatch(fetchCompare({ category1, category2 }));
    }
  };

  return (
    <div className="flex items-center space-x-4 ml-4">
      <div>
        <IndexDropDown handleChange={setFromIndex} category={category1} label="From" />
      </div>

      <div>
        <IndexDropDown handleChange={setToIndex} category={category2} label="To" />
      </div>

      <button
        onClick={performCompare}
        className="bg-black text-white font-bold py-2 px-4 rounded"
      >
        Compare
      </button>
    </div>
  );
};

export default CompareCard;
