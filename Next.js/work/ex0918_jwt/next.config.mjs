/** @type {import('next').NextConfig} */
const nextConfig = {
    async rewrites(){
        return[{
            source:"/api/:path*",
            destination: "http://3.34.190.139:8080/api/:path*"
        }]
    }
};

export default nextConfig;
