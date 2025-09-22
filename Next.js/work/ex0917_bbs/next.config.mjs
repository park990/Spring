/** @type {import('next').NextConfig} */
const nextConfig = {
    async rewrites(){
        return[
            {
                source: "/board/:path*",
                destination: "http://localhost:8080/board/:path*"
            }
        ]
    }
};
export default nextConfig;
