import React from 'react';
import { Container } from 'react-bootstrap';
import { useParams } from 'react-router-dom';
import Post from '../component/Post/Post.js';
import { useGlobalContext } from '../context/GlobalContext.js';

function PostPage(props) {
    let { id } = useParams();
    console.log(id);

    const { post } = useGlobalContext();

    return (
        <Container>
            <Post {...post} />
        </Container>
    );
}

export default PostPage;
