import { createContext, useContext, useReducer } from "react";
import toys from "../Components/data/product_data";
import { cartReducer, productReducer } from "./Reducers";
const Cart = createContext();

const Context = ({ children }) => {
  const [state, dispatch] = useReducer(cartReducer, { toys: toys });

  return <Cart.Provider value={{ state, dispatch }}>{children}</Cart.Provider>;
};

export const CartState = () => {
  return useContext(Cart);
};

export default Context;
