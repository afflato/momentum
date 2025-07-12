import { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { fetchNiftyData } from '../features/indexDataSlice';
import { PieChart, Pie, Cell, ResponsiveContainer, Tooltip, Legend } from 'recharts';

export const IndexDetails = () => {
  const PIE_COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042', '#A2D9CE', '#F7CAC9'];

  const { indexName } = useParams();
  const dispatch = useDispatch();

    const niftyData = useSelector((state) => state.indexData.data);
    const loading = useSelector((state) => state.indexData.loading);
    const error = useSelector((state) => state.indexData.error);

  useEffect(() => {
    dispatch(fetchNiftyData(indexName));
  }, [dispatch]);



  if (loading === 'pending') {
    return <div>Loading Nifty data...</div>;
  }

  if (loading === 'failed') {
    return <div>Error: {error}</div>;
  }


  if (!niftyData || !niftyData.latestData || niftyData.latestData.length === 0 || !niftyData.data) {
    return (
      <div className="flex items-center justify-center min-h-screen bg-yellow-100 text-yellow-800 font-inter p-4 rounded-lg shadow-md">
        <div className="text-xl font-semibold">No Nifty data available.</div>
      </div>
    );
  }

  const latestNifty = niftyData.latestData[0];
  const stockData = niftyData.data;

  const pieChartData = [
    { name: 'Advances', value: niftyData.advances },
    { name: 'Declines', value: niftyData.declines },
    { name: 'Unchanged', value: niftyData.unchanged },
  ];

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 p-6 font-inter text-gray-800 antialiased">
      <div className="max-w-7xl mx-auto">
        <header className="mb-8 p-6 bg-white rounded-2xl shadow-xl flex items-center justify-between">
          <Link to="/" >Back</Link>
          <h1 className="text-4xl font-extrabold text-gray-900 tracking-tight">
            Nifty Index Dashboard
          </h1>
          <span className="text-lg text-gray-600">Last Updated: {niftyData.time}</span>
        </header>

        <section className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          <div className="bg-white p-6 rounded-2xl shadow-lg border border-blue-200">
            <h2 className="text-2xl font-bold text-gray-900 mb-2">{latestNifty.indexName}</h2>
            <p className="text-4xl font-extrabold text-indigo-700 mb-2">
              {latestNifty.ltp}
            </p>
            <p className={`text-xl font-semibold ${parseFloat(latestNifty.ch) >= 0 ? 'text-green-600' : 'text-red-600'}`}>
              {parseFloat(latestNifty.ch) >= 0 ? '+' : ''}{latestNifty.ch} ({parseFloat(latestNifty.per) >= 0 ? '+' : ''}{latestNifty.per}%)
            </p>
          </div>

          <div className="bg-white p-6 rounded-2xl shadow-lg border border-blue-200">
            <h3 className="text-lg font-medium text-gray-600">Today's High</h3>
            <p className="text-3xl font-bold text-gray-800">{latestNifty.high}</p>
          </div>

          <div className="bg-white p-6 rounded-2xl shadow-lg border border-blue-200">
            <h3 className="text-lg font-medium text-gray-600">Today's Low</h3>
            <p className="text-3xl font-bold text-gray-800">{latestNifty.low}</p>
          </div>

          <div className="bg-white p-6 rounded-2xl shadow-lg border border-blue-200">
            <h3 className="text-lg font-medium text-gray-600">Open Price</h3>
            <p className="text-3xl font-bold text-gray-800">{latestNifty.open}</p>
          </div>
        </section>

        <section className="bg-white p-6 rounded-2xl shadow-xl mb-8 flex flex-col lg:flex-row items-center justify-center">
          <div className="w-full lg:w-1/2 h-64 flex items-center justify-center">
            <ResponsiveContainer width="100%" height="100%">
              { <PieChart>
                <Pie
                  data={pieChartData}
                  cx="50%"
                  cy="50%"
                  innerRadius={60}
                  outerRadius={90}
                  fill="#8884d8"
                  paddingAngle={5}
                  dataKey="value"
                  label={({ name, percent }) => `${name}: ${(percent * 100).toFixed(0)}%`}
                >
                  {pieChartData.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={PIE_COLORS[index % PIE_COLORS.length]} />
                  ))}
                </Pie>
                <Tooltip />
                <Legend />
              </PieChart> }
            </ResponsiveContainer>
          </div>
          <div className="w-full lg:w-1/2 p-4 text-center lg:text-left">
            <h2 className="text-3xl font-bold text-gray-900 mb-4">Market Breadth</h2>
            <p className="text-xl text-green-700 font-semibold mb-2">
              Advances: <span className="text-2xl">{niftyData.advances}</span>
            </p>
            <p className="text-xl text-red-700 font-semibold mb-2">
              Declines: <span className="text-2xl">{niftyData.declines}</span>
            </p>
            <p className="text-xl text-gray-700 font-semibold">
              Unchanged: <span className="text-2xl">{niftyData.unchanged}</span>
            </p>
          </div>
        </section>

        <section className="bg-white p-6 rounded-2xl shadow-xl overflow-x-auto">
          <h2 className="text-3xl font-bold text-gray-900 mb-6 text-center">Individual Stock Performance</h2>
          <table className="min-w-full divide-y divide-gray-200 rounded-lg overflow-hidden">
            <thead className="bg-gray-50">
              <tr>
                <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Symbol
                </th>
                <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Last Price (LTP)
                </th>
                <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Change (Pts)
                </th>
                <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Change (%)
                </th>
                <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Traded Volume (M)
                </th>
                <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Market Value (M)
                </th>
                <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Open
                </th>
                <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  High
                </th>
                <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Low
                </th>
                <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Previous Close
                </th>
                <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Day End Close
                </th>
                <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Corporate Action
                </th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {stockData.map((stock, index) => (
                <tr key={index} className={index % 2 === 0 ? 'bg-gray-50' : 'bg-white'}>
                  <td className="px-6 py-4 whitespace-nowrap text-sm font-semibold text-gray-900">{stock.symbol}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{stock.ltP}</td>
                  <td className={`px-6 py-4 whitespace-nowrap text-sm font-medium ${parseFloat(stock.ptsC) >= 0 ? 'text-green-600' : 'text-red-600'}`}>
                    {parseFloat(stock.ptsC) >= 0 ? '+' : ''}{stock.ptsC}
                  </td>
                  <td className={`px-6 py-4 whitespace-nowrap text-sm font-medium ${parseFloat(stock.iislPercChange) >= 0 ? 'text-green-600' : 'text-red-600'}`}>
                    {parseFloat(stock.iislPercChange) >= 0 ? '+' : ''}{stock.iislPercChange}%
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{stock.trdVol}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{stock.mVal}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{stock.open}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{stock.high}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{stock.low}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{stock.previousClose}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{stock.dayEndClose}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{stock.cAct}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>
      </div>
    </div>
  );
};