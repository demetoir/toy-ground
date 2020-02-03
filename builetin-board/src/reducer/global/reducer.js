import React from 'react';
import actionTable from './actionMap.js';

const reducer = (state, action) => {
    const { type, data } = action;

    if (!(type in actionTable)) {
        // eslint-disable-next-line no-console
        console.error(`unexpected action.type: ${type}`);
        return state;
    }

    return actionTable[type](state, data);
};

export default reducer;
