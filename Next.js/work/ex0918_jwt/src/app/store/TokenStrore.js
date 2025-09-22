import { create } from "zustand";


const TokenStore = create((set) => ({
  accessToken: null,
  setToken(token){
    set({accessToken: token})

  },

}));
export default TokenStore;