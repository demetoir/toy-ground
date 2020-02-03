import actions from './actions.js';
import { onReset } from './handler.js';

const actionTable = {
    [actions.reset]: onReset,
};

export default actionTable;
