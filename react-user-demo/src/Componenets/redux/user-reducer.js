import { combineReducers } from "redux";

const initialState = {
    users: [],
    user: {},

}

const userReducer = (state = initialState, action) => {

    if (action.type === "GET_USERS") {

        return { ...state, users: action.payload };

    }
    if (action.type === "ADD_USER") {

        return state

    }
    if (action.type === "DELETE_USER") {

        return state

    }
    if (action.type === "EDIT_VIEW_DATA") {

        return { ...state, user: action.payload };

    }

    return state;

};
export const usersReducer = combineReducers({
    userData: userReducer
})
