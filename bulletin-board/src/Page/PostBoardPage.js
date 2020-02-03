import React from 'react';
import { Container } from 'react-bootstrap';
import { useGlobalContext } from '../context/GlobalContext.js';
import PostBoard from '../component/Post/PostBoard.js';

function PostBoardPage() {
    const { postBoard } = useGlobalContext();
    const { currentPage, posts, pageCount } = postBoard;

    return (
        <Container>
            <PostBoard
                posts={posts}
                currentPage={currentPage}
                pageCount={pageCount}
            />
        </Container>
    );
}

export default PostBoardPage;
