import React, { useState } from 'react';
import { Button, Container, Row } from 'react-bootstrap';
import { PostTable } from '../component/Post/PostTable.js';
import SearchBar from '../component/SerachBar.js';
import Paginate from '../component/Paginate.js';

function PostBoardPage({ posts }) {
    const [page, setPage] = useState(0);

    const onChangePage = page => {
        // todo
        console.log(page);
        setPage(page);
    };

    const onAddPost = () => {
        // todo
        console.log('add post');
    };

    return (
        <Container>
            <Row>
                <h1>post board</h1>
            </Row>
            <hr />

            <PostTable posts={posts} />

            <Paginate
                pageCount={posts.length}
                marginPagesDisplayed={1}
                pageRangeDisplayed={7}
                onPageChange={onChangePage}
            />

            <SearchBar />

            <Row>
                <Button onClick={onAddPost}>add Post</Button>
            </Row>
        </Container>
    );
}

export default PostBoardPage;
