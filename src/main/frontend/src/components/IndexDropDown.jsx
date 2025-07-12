import {  useSelector } from "react-redux";


const IndexDropDown = ({ label, handleChange, category }) => {
  const { indexes, status, error } = useSelector((state) => state.indexNames);


  let jsonIndexes;

  if (status === 'succeeded') {
    jsonIndexes = indexes.filter(indexData => indexData.type === 'csv');
  }

  return (
    <div className="flex items-center space-x-2">
      <label htmlFor="index" className="text-sm font-medium text-gray-700">
        {label}
      </label>
      <select
        onChange={handleChange}
        id={`dropdown${label}`}
        name="dropdown1"
        value={category || ""} 
        className="block w-full pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm rounded-md"
      >
        <option value="">&nbsp;</option>
        {status === "loading" && <option>Loading...</option>}
        {status === "succeeded" &&
          jsonIndexes.map((index) => (
            <option key={index.name} value={index.name}>
              {index.name}
            </option>
          ))}
        {status === "failed" && <option>Error: {error}</option>}
      </select>
    </div>
  );
};

export default IndexDropDown;
