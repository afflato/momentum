import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";


const API_BASE_URL = import.meta.env.VITE_MT_BE_API_BASE_URL;

export const fetchMomentum = createAsyncThunk(
  "indexes/fetchMomentum",
  async (selectedValue) => {
    const response = await axios.get(
      `${API_BASE_URL}/v1/momentum?cat=${selectedValue}`
    );
    return response.data;
  }
);

const momentumSlice = createSlice({
  name: "momentum",
  initialState: {
    momentum: {},
    status: "idle",
    error: null,
  },
  reducers: {
    clearMomentumData: (state) => {
      state.momentum = null;
      state.status = 'idle';
      state.error = null;
    },
    setStrategyIndex: (state, action) => {
      state.strategyIndex = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchMomentum.pending, (state) => {
        state.status = "loading";
      })
      .addCase(fetchMomentum.fulfilled, (state, action) => {
        state.status = "succeeded";
        state.momentum = action.payload;
      })
      .addCase(fetchMomentum.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message;
      });
  },
});

export const { clearMomentumData, setStrategyIndex } = momentumSlice.actions;
export default momentumSlice.reducer;
