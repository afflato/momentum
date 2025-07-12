import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";


const API_BASE_URL = import.meta.env.VITE_MT_BE_API_BASE_URL;

export const fetchIndexNames = createAsyncThunk(
  "indexes/fetchIndexNames",
  async () => {
    const response = await axios.get(`${API_BASE_URL}/v1/indexData/names`);
    return response.data;
  }
);

const indexNamesSlice = createSlice({
  name: "indexNames",
  initialState: {
    indexes: [],
    status: "idle",
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchIndexNames.pending, (state) => {
        state.status = "loading";
      })
      .addCase(fetchIndexNames.fulfilled, (state, action) => {
        state.status = "succeeded";
        state.indexes = action.payload;
      })
      .addCase(fetchIndexNames.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message;
      });
  },
});

export default indexNamesSlice.reducer;