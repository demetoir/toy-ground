import React, { createContext, useContext } from 'react';

const GlobalContext = createContext([]);

export function GlobalProvider({ children, value }) {
    return (
        <GlobalContext.Provider value={value}>
            {children}
        </GlobalContext.Provider>
    );
}

export function useGlobalContext() {
    return useContext(GlobalContext);
}
