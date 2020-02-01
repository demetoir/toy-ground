import React from 'react';
import { useLocation } from 'react-router-dom';

function Page404() {
    let location = useLocation();

    // todo go back to home page redirect
    return <div>Page404 on {location.pathname}</div>;
}

export default Page404;
