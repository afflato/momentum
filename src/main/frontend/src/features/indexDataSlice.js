import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

const API_BASE_URL = import.meta.env.VITE_MT_BE_API_BASE_URL;

export const fetchNiftyData = createAsyncThunk(
  'nifty/fetchNiftyData',
  async (indexName, { rejectWithValue }) => {
    try {
      const response = await fetch(`${API_BASE_URL}/v1/nifty?indexName=${indexName}`);

      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`HTTP error! status: ${response.status}, message: ${errorText}`);
      }

      const data = await response.json();
      return data;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);

const indexDataSlice = createSlice({
  name: 'nifty',
  initialState: {
    data: null,
    loading: 'idle', // 'idle' | 'pending' | 'succeeded' | 'failed'
    error: null,
  },
  reducers: {
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchNiftyData.pending, (state) => {
        state.loading = 'pending';
        state.error = null;
      })
      .addCase(fetchNiftyData.fulfilled, (state, action) => {
        state.loading = 'succeeded';
        state.data = action.payload;
      })
      .addCase(fetchNiftyData.rejected, (state, action) => {
        state.loading = 'failed';
        state.error = action.payload;
        state.data = null;
      });
  },
});

export default indexDataSlice.reducer;
