/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  env: {
    SERVER_API: "https://immunet.azurewebsites.net",
  },
};

module.exports = nextConfig;
