import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import PostBoardPage from '../Page/PostBoardPage.js';
import PostEditorPage from '../Page/PostEditorPage.js';
import dummyPosts from '../mock/dummyPosts.js';
import dummyPost from '../mock/dummyPost.js';
import PostPage from '../Page/PostPage.js';
import Page404 from '../Page/Page404.js';

function App() {
    return (
        <Router>
            <Switch>
                <Route exact path="/">
                    <PostBoardPage posts={dummyPosts} />
                </Route>
                <Route path="/post/:id">
                    <PostPage {...dummyPost} />
                </Route>
                <Route path="/postEditor">
                    <PostEditorPage post={dummyPost} />
                </Route>
                <Route path="*">
                    <Page404/>
                </Route>
            </Switch>
        </Router>
    );
}

export default App;
