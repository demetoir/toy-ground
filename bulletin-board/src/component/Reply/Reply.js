import { Button, Col, Row } from 'react-bootstrap';
import React from 'react';
import useToggle from '../../hooks/useToggle.js';
import ReplyDepthsPadding from './ReplyDepthsPadding.js';
import ReplyForm from './ReplyForm.js';

function Reply(props) {
    const { userId, date, contents, depth, postId, id } = props;

    const isAddReply = useToggle();
    const isEditReply = useToggle();

    // todo
    const onAddReply = () => {
        isAddReply.toggle();
        console.log('onAddReply');
    };

    // todo
    const onEditReply = () => {
        isEditReply.toggle();
        console.log('onEditReply');
    };

    // todo
    const onDeleteReply = () => {
        console.log('onDeleteReply');
    };

    return (
        <Row>
            <ReplyDepthsPadding depth={depth} />
            <Col>
                <Row>
                    <Col>
                        <h5>{userId}</h5>
                    </Col>

                    <Col>
                        <h6>{date}</h6>
                    </Col>

                    <Button
                        onClick={onAddReply}
                        size={'sm'}
                        variant={'primary'}
                    >
                        add
                    </Button>

                    <Button onClick={onEditReply} size={'sm'} variant={'info'}>
                        edit{' '}
                    </Button>
                    <Button
                        onClick={onDeleteReply}
                        size={'sm'}
                        variant={'danger'}
                    >
                        delete{' '}
                    </Button>
                </Row>

                {isEditReply.state ? (
                    <>
                        <hr />
                        <ReplyForm
                            postId={postId}
                            replyParentId={id}
                            replyParentDepth={depth}
                            onSubmit={isEditReply.toggle}
                            contents={contents}
                        />
                        <hr />
                    </>
                ) : (
                    <Row>
                        <p>{contents}</p>
                    </Row>
                )}

                {isAddReply.state ? (
                    <ReplyForm
                        postId={postId}
                        replyParentId={id}
                        replyParentDepth={depth}
                        onSubmit={isAddReply.toggle}
                    />
                ) : null}
            </Col>
        </Row>
    );
}

export default Reply;
