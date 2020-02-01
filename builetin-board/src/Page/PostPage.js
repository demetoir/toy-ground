import React from 'react';
import { Container } from 'react-bootstrap';
import { useParams } from 'react-router-dom';
import Post from '../component/Post/Post.js';

function PostPage(props) {
    let { id } = useParams();
    console.log(id);
    return (
        <Container>
            <Post {...props} />
        </Container>
    );
}

export default PostPage;
