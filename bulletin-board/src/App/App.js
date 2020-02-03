import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import dummyPosts from '../mock/dummyPosts.js';
import dummyPost from '../mock/dummyPost.js';
import { GlobalProvider } from '../context/GlobalContext.js';
import dummyBoard from "../mock/dummyBoard.js";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import PostBoardPage from "../Page/PostBoardPage.js";
import PostPage from "../Page/PostPage.js";
import PostEditorPage from "../Page/PostEditorPage.js";
import Page404 from "../Page/Page404.js";

function App() {
    const value = {
        post: dummyPost,
        posts: dummyPosts,
        postBoard:dummyBoard
    };

    return (
        <GlobalProvider value={value}>
            <Router>
                <Switch>
                    <Route exact path="/">
                        <PostBoardPage />
                    </Route>
                    <Route path="/post/:id">
                        <PostPage />
                    </Route>
                    <Route path="/postEditor">
                        <PostEditorPage />
                    </Route>
                    <Route path="*">
                        <Page404 />
                    </Route>
                </Switch>
            </Router>
        </GlobalProvider>
    );
}

export default App;
