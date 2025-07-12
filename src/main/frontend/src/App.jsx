import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider,
} from "react-router-dom";
import HomePage from "./pages/HomePage";
import MainLayout from "./layouts/MainLayout";
import IndicesPage from "./pages/IndicesPage";
import ComparePage from "./pages/ComparePage";
import NotFoundPage from "./pages/NotFoundPage";
import { Provider } from "react-redux";
import { store } from "./app/store";

import { IndexDetails } from "./components/IndexDetails";
import { fetchIndexNames } from "./features/indexNamesSlice";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<MainLayout />}>
      <Route index element={<HomePage />} />
      <Route path="/indices" element={<IndicesPage />} />
      <Route path="/compare" element={<ComparePage />} />
      <Route path="/index-details/:indexName" element={<IndexDetails />} />

      <Route path="*" element={<NotFoundPage />} />
    </Route>
  )
);

store.dispatch(fetchIndexNames());

const App = () => {
  return (
    <Provider store={store}>
      <RouterProvider router={router} />
    </Provider>
  );
};

export default App;
