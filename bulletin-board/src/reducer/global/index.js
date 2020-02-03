import actions from './actions.js';
import useReducerWrapper from './useReducer.js';

export default {
    globalActions: actions,
    useGlobal: useReducerWrapper,
};
