import React, { useState, useEffect } from 'react';

const MySessions = () => {
    const [sessions, setSessions] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchSessions = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/counselling/my-sessions', {
                    credentials: 'include',
                });
                const data = await response.json();
                if (response.ok) {
                    setSessions(data.data);
                } else {
                    setError(data.message || 'Failed to fetch sessions');
                }
            } catch (err) {
                setError('Failed to connect to the server.');
            }
        };
        fetchSessions();
    }, []);

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold mb-4">My Sessions</h1>
            {error && <p className="text-red-500">{error}</p>}
            <div>
                {sessions.map((session) => (
                    <div key={session.session_id} className="border p-4 rounded-lg mb-4">
                        <h2 className="text-xl font-bold">{session.counsellorName}</h2>
                        <p className="text-gray-600">{session.specialization}</p>
                        <p className="mt-2">Time: {new Date(session.sessionTime).toLocaleString()}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default MySessions;