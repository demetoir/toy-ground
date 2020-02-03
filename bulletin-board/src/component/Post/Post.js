import React from 'react';
import { Col, Row } from 'react-bootstrap';
import PostMeta from './PostMeta.js';
import ReplyList from '../Reply/ReplyList.js';
import ReplyForm from '../Reply/ReplyForm.js';
import PostButtonToolBar from './PostButtonToolBar.js';

function PostTitle({ title }) {
    return (
        <Row>
            <Col>
                <h1>{title}</h1>
            </Col>
        </Row>
    );
}

function Post(props) {
    const { title, contents, replies, id } = props;

    return (
        <>
            <PostTitle title={title} />
            <PostButtonToolBar />
            <hr />
            <PostMeta {...props} />
            <hr />
            <Row>{contents}</Row>
            <hr />
            <ReplyList data={replies} />
            <ReplyForm
                postId={id}
                replyParentId={null}
                replyParentDepth={null}
            />
        </>
    );
}

export default Post;
