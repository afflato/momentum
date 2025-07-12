
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_MT_BE_API_BASE_URL;


export const fetchCompare = createAsyncThunk(
  "indexes/fetchMomentum",
  async ({ category1, category2 }) => {
    const response = await axios.get(
      `${API_BASE_URL}/v1/compare?cat1=${category1}&cat2=${category2}`
    );
    return response.data;
  }
);

const compareSlice = createSlice({
  name: "compare",
  initialState: {
    category1: "",
    category2: "",
    compare: {},
    status: "idle",
    error: null,
  },
  reducers: {
    clearCompareData: (state) => {
      state.compare = null;
      state.status = 'idle';
      state.error = null;
    },
    setCategory1: (state, action) => {
      state.category1 = action.payload;
    },
    setCategory2: (state, action) => {
      state.category2 = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchCompare.pending, (state) => {
        state.status = "loading";
      })
      .addCase(fetchCompare.fulfilled, (state, action) => {
        state.status = "succeeded";
        state.compare = action.payload;
      })
      .addCase(fetchCompare.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message;
      });
  },
});

export const { setCategory1, setCategory2, clearCompareData } = compareSlice.actions;

export default compareSlice.reducer;
