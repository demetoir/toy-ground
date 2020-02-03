import React, { useState } from 'react';
import { Button, Container, Row } from 'react-bootstrap';
import { PostTable } from './PostTable.js';
import Box from '../Box.js';
import Paginate from '../Paginate.js';
import SearchBar from '../SerachBar.js';

function PostBoard({ posts, currentPage, pageCount }) {
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
            <Box>
                <Paginate
                    pageCount={pageCount}
                    forcePage={currentPage}
                    marginPagesDisplayed={1}
                    pageRangeDisplayed={7}
                    onPageChange={onChangePage}
                />
            </Box>
            <Box>
                <SearchBar />
            </Box>
            <Box>
                <Button onClick={onAddPost}>add Post</Button>
            </Box>
        </Container>
    );
}

export default PostBoard;
