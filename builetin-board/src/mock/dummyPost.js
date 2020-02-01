const dummyPost = {
    id: 123,
    title: 'long title',
    date: 'date',
    userId: 'userId',
    star: 333,
    viewCount: 222,
    replies: [
        {
            postId: 123,
            id: 0,
            depth: 0,
            userId: 'userId',
            date: 'date',
            contents: 'sefsef',
        },
        {
            postId: 123,
            id: 1,
            depth: 0,
            userId: 'userId',
            date: 'date',
            contents: 'sefsef',
        },
        {
            postId: 123,
            id: 2,
            depth: 1,
            userId: 'userId',
            date: 'date',
            contents: 'sefsef',
        },
        {
            postId: 123,
            id: 3,
            depth: 1,
            userId: 'userId',
            date: 'date',
            contents: 'sefsef',
        },
        {
            postId: 123,
            id: 4,
            depth: 2,
            userId: 'userId',
            date: 'date',
            contents: 'sefsef',
        },
    ],
    contents: 'post body',
};

export default dummyPost;
