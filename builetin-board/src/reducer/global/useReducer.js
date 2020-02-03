import React, { useReducer } from 'react';
import reducer from './reducer.js';
import initState from './initialState.js';

function useReducerWrapper() {
    let [state, dispatch] = useReducer(reducer, initState);

    return { state, dispatch };
}

export default useReducerWrapper;
