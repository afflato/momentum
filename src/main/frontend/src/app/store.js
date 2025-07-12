import { configureStore } from "@reduxjs/toolkit";
import indexReducer from "../features/indexSlice";
import indexNamesReducer from "../features/indexNamesSlice";
import momentumReducer from "../features/momentumSlice";
import compareReducer from "../features/compareSlice";
import indexDataReducer from "../features/indexDataSlice";

export const store = configureStore({
  reducer: {
    indexes: indexReducer,
    momentum: momentumReducer,
    compare: compareReducer,
    indexNames: indexNamesReducer,
    indexData: indexDataReducer

  },
});
