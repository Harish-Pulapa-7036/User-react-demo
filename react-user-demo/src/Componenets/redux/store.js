import { createStore } from "redux";
import { usersReducer } from "./user-reducer";



const store = createStore(usersReducer)
export default store;