import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';


export const IndexNameCard = () => {
  const { indexes, status, error } = useSelector((state) => state.indexNames);

  let content;

  if (status === 'loading') {
    content = (
      <div className="flex justify-center items-center p-4">
        <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-gray-900"></div>
        <p className="ml-2 text-gray-700">Loading index names...</p>
      </div>
    );
  } else if (status === 'succeeded') {
    const jsonIndexes = indexes.filter(indexData => indexData.type === 'json');

    content = (
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 p-4">
        {jsonIndexes.length > 0 ? (
          jsonIndexes.map((indexData) => (
            <Link
              key={indexData.name}
              to={`/index-details/${indexData.name}`}
              className="block"
            >
              <div
                className="bg-white p-4 rounded-lg shadow-md hover:shadow-lg transition-shadow duration-200 cursor-pointer h-full flex flex-col justify-between" // Add cursor-pointer and flex for better layout
              >
                <h3 className="text-lg font-semibold text-gray-800 break-words text-center">
                  {indexData.name}
                </h3>

              </div>
            </Link>
          ))
        ) : (
          <p className="text-center text-gray-500 col-span-full">No JSON-type index names found.</p>
        )}
      </div>
    );
  } else if (status === 'failed') {
    content = (
      <div className="p-4 bg-red-100 border border-red-400 text-red-700 rounded-lg mx-auto max-w-md">
        <p className="font-bold">Error:</p>
        <p>{error || 'Failed to fetch index names.'}</p>
        <p className="text-sm mt-2">Please check the API endpoint or network connection.</p>
      </div>
    );
  }

  return (
    <div className="flex flex-col items-center min-h-screen bg-gray-100 font-sans p-4">
      <h2 className="text-3xl font-extrabold text-gray-900 mb-6 mt-4 rounded-lg p-3 bg-gradient-to-r from-purple-400 to-blue-500 text-white shadow-lg">
        Broad Nifty Market Categorization
      </h2>
      <div className="w-full">
        {content}
      </div>
    </div>
  );
};
