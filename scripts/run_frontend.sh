#!/bin/bash

# Navigate to the frontend directory
cd frontend || exit

# Install dependencies
pnpm install

# Build the project
pnpm run build

# Start the project
pnpm run start