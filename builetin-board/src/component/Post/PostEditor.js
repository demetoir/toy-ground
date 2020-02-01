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
            <Row>
                <Col>
                    <h1>post editor</h1>
                </Col>
            </Row>

            <Row>
                <Col>
                    <Form.Group controlId="exampleForm.PostTitle">
                        <Form.Label>post title</Form.Label>
                        <Form.Control type="input" defaultValue={post.title} ref={postTitleRef}/>
                    </Form.Group>
                    <Form.Group controlId="exampleForm.PostBody">
                        <Form.Label>post body</Form.Label>
                        <Form.Control
                            as="textarea"
                            rows="3"
                            defaultValue={post.contents}
                            ref={postBodyRef}
                        />
                    </Form.Group>
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
