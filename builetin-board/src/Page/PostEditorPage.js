import PostEditor from '../component/Post/PostEditor.js';
import React from 'react';
import { Container } from 'react-bootstrap';
import { useGlobalContext } from '../context/GlobalContext.js';

function PostEditorPage() {
    const { post } = useGlobalContext();

    return (
        <Container>
            <h1>post editor</h1>

            <PostEditor post={post} />
        </Container>
    );
}

export default PostEditorPage;
