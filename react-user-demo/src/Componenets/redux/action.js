export const addUser = () => {
    return {
        type: "ADD_USER",
        // payload:user
    }
}
export const DeleteUser = () => {
    return {
        type: "DELETE_USER",
        // payload:user
    }
}
export const getUsers = (userData) => {

    return {

        type: "GET_USERS",

        payload: userData,

    };

};
export const editViewData = (user) => {

    return {

        type: "EDIT_VIEW_DATA",

        payload: user,

    };

};