import { create } from "zustand";


const TokenStore = create((set) => ({
  accessToken: 1,
  setToken(token){
    set({accessToken: token})

  },

}));
export default TokenStore;