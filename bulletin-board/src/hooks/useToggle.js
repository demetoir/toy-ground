import { useState } from 'react';

function useToggle(initialValue = false) {
    const [state, setState] = useState(initialValue);
    const toggle = () => setState(!state);
    const reset = () => setState(initialValue);

    return { state, toggle, reset };
}

export default useToggle;
