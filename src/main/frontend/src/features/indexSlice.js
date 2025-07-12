import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_MT_BE_API_BASE_URL;


export const fetchIndexes = createAsyncThunk(
  "indexes/fetchIndexes",
  async () => {
    const response = await axios.get(`${API_BASE_URL}/v1/indices`);
    return response.data;
  }
);

const indexSlice = createSlice({
  name: "indexes",
  initialState: {
    indexes: [],
    status: "idle",
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchIndexes.pending, (state) => {
        state.status = "loading";
      })
      .addCase(fetchIndexes.fulfilled, (state, action) => {
        state.status = "succeeded";
        state.indexes = action.payload;
      })
      .addCase(fetchIndexes.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message;
      });
  },
});


export default indexSlice.reducer;
