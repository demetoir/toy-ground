import React, { useRef } from 'react';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';

function PostEditor(props) {
    const { post } = props;
    console.log(post);

    const postTitleRef = useRef();
    const postBodyRef = useRef();

    // todo
    const onCancel = () => {};

    // todo
    const onSubmit = () => {
        console.log(postTitleRef.current.value);
        console.log(postBodyRef.current.value);
    };

    return (
        <Container>
            <h1>post editor</h1>
            <Row>
                <Col>
                    <Form.Label>post title</Form.Label>
                    <Form.Control
                        type="input"
                        defaultValue={post.title}
                        ref={postTitleRef}
                    />
                    <Form.Label>post body</Form.Label>
                    <Form.Control
                        as="textarea"
                        rows="3"
                        defaultValue={post.contents}
                        ref={postBodyRef}
                    />
                </Col>
            </Row>

            <Row>
                <Col>
                    <Button onClick={onSubmit}>submit</Button>
                </Col>

                <Col>
                    <Button onClick={onCancel}>cancel</Button>
                </Col>
            </Row>
        </Container>
    );
}

export default PostEditor;
