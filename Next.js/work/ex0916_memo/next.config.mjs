/** @type {import('next').NextConfig} */
const nextConfig = {
    async rewrites(){
        return[
            {
                destination:"http://localhost:8080/memo/:path*",
                source:"/memo/:path*"
            }
        ]
    }
};

export default nextConfig;
